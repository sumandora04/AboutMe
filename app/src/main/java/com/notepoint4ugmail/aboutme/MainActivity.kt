package com.notepoint4ugmail.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.notepoint4ugmail.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // ActivityMainBinding is auto generated by the compiler. It is the combination of "activity_main" xml file and "Binding"

    private val elephantObject: Elephant = Elephant("21 amazing elephant facts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // Remove this function when using data binding
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        ) // Initialise the binding variable and put the setContentView here.

//        findViewById<Button>(R.id.done_btn).setOnClickListener {
//            addAuthorName(it) // here it refers to the current view-- ie -- done button.
//        }


        /*
        *When the binding object is created, the compiler generates the names of the views in the binding object
         *  from the IDs of the views in the layout, converting them to camel case.
         *  So, for example, done_button is doneButton in the binding object.
        * */

        binding.elephant = elephantObject

        binding.doneBtn.setOnClickListener {
            addAuthorName(it)
        }
    }

    private fun addAuthorName(view: View) {
        /* val authorNameEd: EditText = findViewById(R.id.author_title_edit)
         val authorNameTv: TextView = findViewById(R.id.author_name_text)

         authorNameTv.text = authorNameEd.text // Getting text value from editText and setting it to the textView

         //Changing the visibility of views
         authorNameEd.visibility = View.GONE
         view.visibility = View.GONE
         authorNameTv.visibility = View.VISIBLE*/

        //OnBind:
        binding.apply {
            //  binding.authorNameText.text = binding.authorTitleEdit.text.toString() // While using binding, it is necessary to explicitly convert the editText value to toString().

            elephant?.author = binding.authorTitleEdit.text.toString() // Binding data. //
            //  elephantObject.author = binding.authorTitleEdit.text.toString() // This one also works

            invalidateAll() // To reflect the changes.
            binding.authorNameText.visibility = View.VISIBLE
            binding.authorTitleEdit.visibility = View.GONE
            binding.doneBtn.visibility = View.GONE
        }


        //Hide the keyboard:
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
