package response

data class ContactResponse(
    val id : Int,
    val message : String,
    val created_at : String,
    val user : Int?
)