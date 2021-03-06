package mapper

import com.drogariadopovo.domain.model.Worked
import response.WorkedResponse
import javax.inject.Inject

class WorkedMapper @Inject constructor(){
    fun map (response : WorkedResponse) : Worked {
        return Worked(
            worked = response.worked,
            detail = response.detail
        )
    }
}