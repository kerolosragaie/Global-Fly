package com.kerollosragaie.globalfly.models

import java.io.Serializable

//class Destination : ArrayList<DestinationItem>()

data class Destination(
    var id: Int = 0,
    var image: String? = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1200px-No-Image-Placeholder.svg.png",
    var city: String? = null,
    var description: String? = null,
    var country: String? = null
) : Serializable

