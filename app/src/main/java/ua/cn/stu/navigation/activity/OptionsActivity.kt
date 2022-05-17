package ua.cn.stu.navigation.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import ua.cn.stu.navigation.R
import ua.cn.stu.navigation.activity.model.Options
import ua.cn.stu.navigation.databinding.ActivityOptionsBinding

class OptionsActivity : BaseActivity() {

    private lateinit var binding: ActivityOptionsBinding

    private lateinit var options: Options

    private lateinit var thimbleCountItems: List<ThimbleCountItem>
    private lateinit var adapter: ArrayAdapter<ThimbleCountItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options = savedInstanceState?.getParcelable<Options>(KEY_OPTIONS) ?:
                intent?.getParcelableExtra(EXTRA_OPTIONS) ?:
                throw IllegalArgumentException("You need to specify EXTRA_OPTIONS to launch this activity")

        setupSpinner()
        updateUi()

        binding.cancelButton.setOnClickListener { onCancelPressed() }
        binding.confirmButton.setOnClickListener { onConfirmPressed() }
    }

    private fun setupSpinner() {
        thimbleCountItems = (1..6).map { ThimbleCountItem(it, resources.getQuantityString(R.plurals.thimbles, it, it)) }
        adapter = ArrayAdapter(
            this,
            R.layout.item_spinner,
            thimbleCountItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

        binding.thimbleCountSpinner.adapter = adapter
        binding.thimbleCountSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val count = thimbleCountItems[position].count
                options = options.copy(thimbleCount = count)
            }
        }
    }

    private fun updateUi() {
        binding.enableTimerCheckBox.isChecked = options.isTimerEnabled

        val currentIndex = thimbleCountItems.indexOfFirst { it.count == options.thimbleCount }
        binding.thimbleCountSpinner.setSelection(currentIndex)
    }

    private fun onCancelPressed() {
        finish()
    }

    private fun onConfirmPressed() {
        options = options.copy(isTimerEnabled = binding.enableTimerCheckBox.isChecked)
        val intent = Intent()
        intent.putExtra(EXTRA_OPTIONS, options)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_OPTIONS = "EXTRA_OPTIONS"

        private const val KEY_OPTIONS = "KEY_OPTIONS"
    }

    class ThimbleCountItem(
        val count: Int,
        private val optionTitle: String
    ) {
        override fun toString(): String {
            return optionTitle
        }
    }
}