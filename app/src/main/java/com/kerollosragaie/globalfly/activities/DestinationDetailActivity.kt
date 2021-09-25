package com.kerollosragaie.globalfly.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.models.Destination
import com.kerollosragaie.globalfly.services.DestinationService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DestinationDetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_detail)

		setSupportActionBar(detail_toolbar)
		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val bundle: Bundle? = intent.extras

		//This calls current item id but better to call an object as below:
		/*if (bundle?.containsKey(ARG_ITEM_ID)!!) {

			val id = intent.getIntExtra(ARG_ITEM_ID, 0)

			//loadDetails(id)

			initUpdateButton(id)

			initDeleteButton(id)
		}*/
		//Note:
		if(bundle?.containsKey(ARG_DESTINY_OBJECT)!!){
			val currentDestination = intent.extras?.get(ARG_DESTINY_OBJECT) as Destination
			loadDetails(currentDestination)
			initUpdateButton(currentDestination)
			initDeleteButton(currentDestination)
		}
	}

	private fun loadDetails(destination: Destination) {

		destination?.let {
			et_city.setText(destination.city)
			et_description.setText(destination.description)
			et_country.setText(destination.country)
			collapsing_toolbar.title = destination.city
		}
		//To get data with using path by Retrofit
		/*val destinationService : DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
		val requestCall: Call<Destination> = destinationService.getDestination(id = destination.id)
		requestCall.enqueue(object: Callback<Destination>{
			override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
				if(response.isSuccessful){
					et_city.setText(response.body()?.city)
					et_description.setText(response.body()?.description)
					et_country.setText(response.body()?.country)

					collapsing_toolbar.title = response.body()?.city
				}else{//Application level failure:
					Toast.makeText(this@DestinationDetailActivity,"Failed to retrieve items",Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<Destination>, t: Throwable) {
				Toast.makeText(this@DestinationDetailActivity,t.toString(), Toast.LENGTH_LONG).show()

			}

		})*/
	}

	private fun initUpdateButton(destination: Destination) {

		btn_update.setOnClickListener {

            // To be replaced by retrofit code
            /*val destination = Destination()
            destination.id = id
            destination.city = city
            destination.description = description
            destination.country = country*/

            SampleData.updateDestination(destination)
            finish() // Move back to DestinationListActivity
		}
	}

	private fun initDeleteButton(destination: Destination) {

		btn_delete.setOnClickListener {

            // To be replaced by retrofit code
            SampleData.deleteDestination(destination.id)
            finish() // Move back to DestinationListActivity
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		val id = item.itemId
		if (id == android.R.id.home) {
			navigateUpTo(Intent(this, DestinationListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {

		const val ARG_ITEM_ID = "item_id"

		const val ARG_DESTINY_OBJECT = "item_id"
	}
}
