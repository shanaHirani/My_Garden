package com.example.mygarden.data.model.domainModel

import com.example.mygarden.R


enum class PlantType(
    val iconDrawableId : Int,
    val typename:String
){
    Tree(
        R.drawable.ic_tree,
        "tree"
    ),
    InDoor(
        R.drawable.ic_indoor_plants,
        "in door"
    ),
    OutDoor(
        R.drawable.ic_outdoor_plant,
        "out door"
    )
}

data class Plant(
    val plantId: String,
    val name: String,
    val description: String,
    val plantType: PlantType,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
)/* {

    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name
}*/