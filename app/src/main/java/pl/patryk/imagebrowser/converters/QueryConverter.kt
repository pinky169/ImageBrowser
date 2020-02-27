package pl.patryk.imagebrowser.converters

class QueryConverter {

    companion object {
        private var IMAGE_TYPES: HashMap<String, String>
        private var IMAGE_CATEGORIES: HashMap<String, String>
        private var IMAGE_ORIENTATIONS: HashMap<String, String>

        init {
            IMAGE_TYPES = HashMap()
            IMAGE_TYPES["All images"] = "all"
            IMAGE_TYPES["Photos"] = "photo"
            IMAGE_TYPES["Vector graphics"] = "vector"
            IMAGE_TYPES["Illustrations"] = "illustration"

            IMAGE_CATEGORIES = HashMap()
            IMAGE_CATEGORIES["Any category"] = "all"
            IMAGE_CATEGORIES["Fashion"] = "fashion"
            IMAGE_CATEGORIES["Nature"] = "nature"
            IMAGE_CATEGORIES["Backgrounds"] = "backgrounds"
            IMAGE_CATEGORIES["Education"] = "education"
            IMAGE_CATEGORIES["People"] = "people"
            IMAGE_CATEGORIES["Feelings"] = "feelings"
            IMAGE_CATEGORIES["Religion"] = "religion"
            IMAGE_CATEGORIES["Health"] = "health"
            IMAGE_CATEGORIES["Places"] = "places"
            IMAGE_CATEGORIES["Animals"] = "animals"
            IMAGE_CATEGORIES["Industry"] = "industry"
            IMAGE_CATEGORIES["Food"] = "food"
            IMAGE_CATEGORIES["Computer"] = "computer"
            IMAGE_CATEGORIES["Sports"] = "sports"
            IMAGE_CATEGORIES["Transportation"] = "transportation"
            IMAGE_CATEGORIES["Travel"] = "travel"
            IMAGE_CATEGORIES["Buildings"] = "buildings"
            IMAGE_CATEGORIES["Business"] = "business"
            IMAGE_CATEGORIES["Music"] = "music"

            IMAGE_ORIENTATIONS = HashMap()
            IMAGE_ORIENTATIONS["Any orientation"] = "all"
            IMAGE_ORIENTATIONS["Horizontal"] = "horizontal"
            IMAGE_ORIENTATIONS["Vertical"] = "vertical"
        }

        fun getImageTypeQuery(key: String): String {
            return IMAGE_TYPES[key]!!
        }

        fun getImageCategoryQuery(key: String): String {
            return IMAGE_CATEGORIES[key]!!
        }

        fun getImageOrientationQuery(key: String): String {
            return IMAGE_ORIENTATIONS[key]!!
        }
    }
}