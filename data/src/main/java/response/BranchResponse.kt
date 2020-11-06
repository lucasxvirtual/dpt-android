package response

data class BranchResponse(
        val id: Int,
        val name: String,
        val phone: String?,
        val points: Int
)