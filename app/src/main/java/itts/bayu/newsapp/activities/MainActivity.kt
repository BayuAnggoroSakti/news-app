package itts.bayu.newsapp.activities

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import itts.bayu.newsapp.R
import itts.bayu.newsapp.fragment.*
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Azhar Rivaldi on 10-04-2021
 * Github : https://github.com/AzharRivaldi
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 * Instagram : https://www.instagram.com/azhardvls_
 * Twitter : https://twitter.com/azharrvldi_
 * Youtube Channel : https://bit.ly/2PJMowZ
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_2)

        bottomNavigation.setItemSelected(R.id.menuHeadline, true)

        setupFragment(FragmentHeadline())

        //open fragment
        bottomNavigation.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                when (id) {
                    R.id.menuHeadline -> setupFragment(FragmentHeadline())
                    R.id.menuSports -> setupFragment(FragmentSports())
                    R.id.menuTechnology -> setupFragment(FragmentTechnology())
                    R.id.menuBusiness -> setupFragment(FragmentBusiness())
//                    R.id.menuHealth -> setupFragment(FragmentHealth())
//                    R.id.menuEntertaiment -> setupFragment(FragmentEntertaiment())
//                    R.id.menuSearch -> setupFragment(FragmentSearch())
                }
            }
        })
    }

    private fun setupFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, fragment)
        transaction.commit()
    }

}