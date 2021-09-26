package com.kerollosragaie.globalfly.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.models.Destination
import com.kerollosragaie.globalfly.services.DestinationService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()

			// To be replaced by retrofit code
			//SampleData.addDestination(newDestination)
			val destinationService : DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
			val requestCall: Call<Destination> = destinationService.addDestination(newDestination)
			requestCall.enqueue(object: Callback<Destination> {
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					if(response.isSuccessful){
						//var newCratedDestination:Destination = response.body()!! //use or ignore it
						finish() // Move back to DestinationListActivity
						Toast.makeText(this@DestinationCreateActivity,"Successfully added", Toast.LENGTH_LONG).show()
					} else{
						Toast.makeText(this@DestinationCreateActivity,"Failed to add item", Toast.LENGTH_LONG).show()
					}
				}

				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationCreateActivity,t.toString(), Toast.LENGTH_LONG).show()
				}
			})
		}
	}
}
