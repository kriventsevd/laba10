package com.example.labs10

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout


class Activity : AppCompatActivity() {
    var colors = arrayOf(0xFFFDFBFC , 0xFF4150E6, 0xFFE64189)
    var sheetNumber = 0
    private lateinit var text : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
       sheetNumber = getIntent().getIntExtra("sheetNumber" , 0)
        text =  findViewById(R.id.text12)
       val next : Button = findViewById(R.id.next)
      next.setOnClickListener(){
           if (sheetNumber < colors.size - 1){
                val intent = Intent (this,this::class.java )
               intent.putExtra("sheetNumber" , sheetNumber + 1 )
               startActivity(intent)
            } else {
               Toast.makeText(applicationContext, getString(R.string.text),Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onPause() {
     super.onPause()
       val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        prefs.putString("note" + sheetNumber ,text.editableText.toString())
       prefs.apply()
  }

    override fun onResume() {
        super.onResume()
        val sheet : ConstraintLayout = findViewById(R.id.sheet)
       sheet.setBackgroundColor(colors[sheetNumber].toInt())
       val saveState = getPreferences(Context.MODE_PRIVATE).getString("note" + sheetNumber.toString(), null)
       if (saveState != null ) {
          text.setText(saveState)
        }

   }
}