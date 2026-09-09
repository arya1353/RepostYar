package com.aryanpour.repostyar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
 private var media: Uri? = null
 private val picker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { media=it; findViewById<TextView>(R.id.fileName).text = it.lastPathSegment ?: "فایل انتخاب شد" } }
 override fun onCreate(state: Bundle?) { super.onCreate(state); setContentView(R.layout.activity_main)
  val caption=findViewById<EditText>(R.id.captionInput); val tags=findViewById<EditText>(R.id.hashtagsInput)
  findViewById<Button>(R.id.pickButton).setOnClickListener { picker.launch("*/*") }
  findViewById<Button>(R.id.captionButton).setOnClickListener { Toast.makeText(this,"در نسخه آزمایشی، کپشن را از اینستاگرام کپی و اینجا جای‌گذاری کنید",Toast.LENGTH_LONG).show() }
  findViewById<Button>(R.id.copyButton).setOnClickListener { val text=(caption.text.toString()+"\n\n"+tags.text).trim(); (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText("caption",text)); Toast.makeText(this,"کپشن کپی شد",Toast.LENGTH_SHORT).show() }
  findViewById<Button>(R.id.instagramButton).setOnClickListener { val i=Intent(Intent.ACTION_SEND).apply { type=media?.let{contentResolver.getType(it)}?:"text/plain"; putExtra(Intent.EXTRA_TEXT,(caption.text.toString()+"\n\n"+tags.text).trim()); media?.let{putExtra(Intent.EXTRA_STREAM,it); addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)}; setPackage("com.instagram.android") }; try{startActivity(i)}catch(e:Exception){startActivity(Intent.createChooser(i.apply{setPackage(null)},"اشتراک‌گذاری"))} }
 }
}
