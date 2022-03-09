package kma.longhoang.coroutines

import com.google.gson.annotations.SerializedName
import java.util.*

class Country {
    var lat: Double = 0.0
    var lng: Double = 0.0
    var country: String = ""
    var countryCode: String = ""
    var totalConfirmed: Double = 0.0
    var totalDeaths: Double = 0.0
    var totalRecovered: Double = 0.0
    var dailyConfirmed: Int = 0
    var dailyDeaths: Int = 0
    var activeCases: Double = 0.0
    var totalCritical: Int = 0
    var totalConfirmedPerMillionPopulation: Double=0.0
    var totalDeathsPerMillionPopulation: Double=0.0
    @SerializedName("FR")
    var fr: Double=0.0
    @SerializedName("PR")
    var pr: Double=0.0
    var lastUpdated: String = ""

}