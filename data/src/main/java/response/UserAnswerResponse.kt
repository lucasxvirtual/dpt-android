package response

import java.io.Serializable

data class UserAnswerResponse (
        val id : Int,
        val answer : String,
        val correct : Boolean?,
        val question : Int
) : Serializable