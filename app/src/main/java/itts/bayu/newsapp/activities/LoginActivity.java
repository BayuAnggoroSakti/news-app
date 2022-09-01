package itts.bayu.newsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import itts.bayu.newsapp.R;
import itts.bayu.newsapp.model.ModelNews;
import itts.bayu.newsapp.model.ModelResultToken;
import itts.bayu.newsapp.networking.ApiEndpoint;
import itts.bayu.newsapp.networking.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    // 华为帐号登录授权服务，提供静默登录接口silentSignIn，获取前台登录视图getSignInIntent，登出signOut等接口
    // Huawei account service, provides silent signIn API silentSignIn, obtain front-end sign-in view API getSignInIntent, sign out API signOut and other APIs
    private AccountAuthService mAuthService;

    // 华为帐号登录授权参数
    // parameter
    private AccountAuthParams mAuthParam;

    // 用户自定义signInIntent请求码
    // User-defined signInIntent request code
    private static final int REQUEST_CODE_SIGN_IN = 1000;

    // 用户自定义日志标记
    // User-defined log mark
    private static final String TAG = "Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main为自定义布局文件名称
        // activity_main is the name of the custom layout file
        setContentView(R.layout.activity_login_2);
        findViewById(R.id.HuaweiIdAuthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silentSignInByHwId();
            }
        });

        findViewById(R.id.ignorelogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SplashAds.class));
            }
        });
        getToken();
    }

    public void subscribe(String topic) {
        try {
            // Subscribe to a topic.
            HmsMessaging.getInstance(this).subscribe(topic)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            // Obtain the topic subscription result.
                            if (task.isSuccessful()) {
                                Log.d(TAG, "subscribe topic successfully");
                            } else {
                                Log.d(TAG, "subscribe topic failed, return value is " + task.getException().getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "subscribe failed, catch exception : " + e.getMessage());
        }
    }
    private void addToken(String token){

        Map<String, String> params = new HashMap<>();

        params.put("token", token);
        ApiInterface apiInterface = (ApiInterface) ApiEndpoint.INSTANCE.getApiClientToken().create(ApiInterface.class);
        Call<ModelResultToken> res = apiInterface.postToken(params);
        res.enqueue(new Callback<ModelResultToken>() {
            @Override
            public void onResponse(Call<ModelResultToken> call,
                                   Response<ModelResultToken> response) {
                Log.d(TAG, "berhasil :"+response.toString());

            }

            @Override
            public void onFailure(Call<ModelResultToken> call, Throwable t)
            {
                // Log error here since request failed
                Log.d(TAG, "gagal :"+t.toString());
            }
        });

    }
    private void getToken() {
        Log.d(TAG, "getToken:begin");

        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = "105773489";
                    String token = HmsInstanceId.getInstance(LoginActivity.this).getToken(appId, "HCM");
                    Log.i(TAG, "get token:" + token);
                    addToken(token);
                    subscribe("berita");
                    if(!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token);

                    }
                    Log.d(TAG, "get token:" + token);
                } catch (ApiException e) {
                    Log.d(TAG, "get token failed, " + e);
                }
            }
        }.start();
    }
    private void sendRegTokenToServer(String token) {
        Log.i(TAG, "sending token to server. token:" + token);
    }
    private void silentSignInByHwId() {

        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();

        // 使用请求参数构造华为帐号登录授权服务AccountAuthService
        // Use request parameters to construct a Huawei account login authorization service AccountAuthService
        mAuthService = AccountAuthManager.getService(this, mAuthParam);

        // 使用静默登录进行华为帐号登录
        // Use silent sign in for HUAWEI ID login
        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {
                dealWithResultOfSignIn(authAccount);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // 静默登录失败，使用getSignInIntent()方法进行前台显式登录
                // Silent sign in fails, use the getSignInIntent() method to log in from the foreground
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                }
            }
        });
    }

    /**
     * 处理返回的AuthAccount，获取帐号信息
     * Process the returned AuthAccount and get account information
     *
     * @param authAccount AccountAccount对象，用于记录帐号信息(AccountAccount object, used to record account information)
     */
    private void dealWithResultOfSignIn(AuthAccount authAccount) {
        //获取帐号信息
        Log.i(TAG, "display name:" + authAccount.getDisplayName());
        Log.i(TAG, "photo uri string:" + authAccount.getAvatarUriString());
        Log.i(TAG, "photo uri:" + authAccount.getAvatarUri());
        Log.i(TAG, "email:" + authAccount.getEmail());
        Log.i(TAG, "openid:" + authAccount.getOpenId());
        Log.i(TAG, "unionid:" + authAccount.getUnionId());
        startActivity(new Intent(LoginActivity.this, SplashAds.class));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                          AuthAccount authAccount = authAccountTask.getResult();
                dealWithResultOfSignIn(authAccount);
                Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            } else {
                // 登录失败，status code标识了失败的原因，请参考API中的错误码参考了解详细错误原因
                // Login failed. The status code identifies the reason for the failure. Please refer to the error
                // code reference in the API for detailed error reasons.
                Log.e(TAG, "sign in failed : " + ((ApiException) authAccountTask.getException()).getStatusCode());
            }
        }
    }

    private void signOut() {
        if (mAuthService == null) {
            return;
        }
        Task<Void> signOutTask = mAuthService.signOut();
        signOutTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");

            }
        });
    }

    private void cancelAuthorization() {
        if (mAuthService == null) {
            return;
        }
        Task<Void> task = mAuthService.cancelAuthorization();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "cancelAuthorization success");

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "cancelAuthorization failure：" + e.getClass().getSimpleName());
            }
        });
    }



}