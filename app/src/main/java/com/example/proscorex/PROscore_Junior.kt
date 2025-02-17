package com.example.proscorex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class PROscore_Junior : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proscore_junior)  // Ensure you have a corresponding layout file

        val btn_HomeFoulAdd = findViewById<Button>(R.id.btn_HomeFoul_Add)
        val btn_HomeFoulSub = findViewById<Button>(R.id.btn_HomeFoul_Sub)
        val lbl_HomeFoulValue = findViewById<TextView>(R.id.lbl_Home_Foul_Value)

        var int_HomeFoul = lbl_HomeFoulValue.text.toString().toInt()

        btn_HomeFoulAdd.setOnClickListener{
            int_HomeFoul++
            if(int_HomeFoul >= 6){
                int_HomeFoul=6
                lbl_HomeFoulValue.text = "P"
            }else{
                lbl_HomeFoulValue.text = int_HomeFoul.toString()
            }
        }

        btn_HomeFoulSub.setOnClickListener{
            int_HomeFoul--
            if(int_HomeFoul <= 0){
                int_HomeFoul=0
                lbl_HomeFoulValue.text = "0"
            }else{
                lbl_HomeFoulValue.text = int_HomeFoul.toString()
            }
        }

        val btn_GuestFoulAdd = findViewById<Button>(R.id.btn_GuestFoul_Add)
        val btn_GuestFoulSub = findViewById<Button>(R.id.btn_GuestFoul_Sub)
        val lbl_GuestFoulValue = findViewById<TextView>(R.id.lbl_Guest_Foul_Value)

        var int_GuestFoul = lbl_GuestFoulValue.text.toString().toInt()

        btn_GuestFoulAdd.setOnClickListener{
            int_GuestFoul++
            if(int_GuestFoul >= 6){
                int_GuestFoul=6
                lbl_GuestFoulValue.text = "P"
            }else{
                lbl_GuestFoulValue.text = int_GuestFoul.toString()
            }
        }

        btn_GuestFoulSub.setOnClickListener{
            int_GuestFoul--
            if(int_GuestFoul <= 0){
                int_GuestFoul=0
                lbl_GuestFoulValue.text = "0"
            }else{
                lbl_GuestFoulValue.text = int_GuestFoul.toString()
            }
        }
        
    }

}