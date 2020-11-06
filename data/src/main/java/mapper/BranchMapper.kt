package mapper

import com.drogariadopovo.domain.model.Branch
import response.BranchResponse
import javax.inject.Inject

class BranchMapper @Inject constructor(){

    fun map(response : List<BranchResponse>) : List<Branch> {
        return response.map { map(it)!! }
    }

    fun map(response : BranchResponse?) : Branch? {
        if(response == null)
            return null
        return Branch(
                id = response.id,
                name = response.name,
                phone = response.phone,
                points = response.points
        )
    }
}