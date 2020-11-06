package mapper

import com.drogariadopovo.domain.model.Role
import response.RoleResponse
import javax.inject.Inject

class RoleMapper @Inject constructor(){
    fun map(response : List<RoleResponse>) : List<Role> {
        return response.map { map(it)!! }
    }

    fun map(response : RoleResponse?) : Role? {
        if(response == null)
            return null
        return Role(
                name = response.name
        )
    }
}