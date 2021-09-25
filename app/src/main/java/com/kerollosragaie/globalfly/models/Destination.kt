package com.kerollosragaie.globalfly.models

import java.io.Serializable

//class Destination : ArrayList<DestinationItem>()

data class Destination (
	var id: Int = 0,
	var city: String? = null,
	var description: String? = null,
	var country: String? = null
):Serializable

