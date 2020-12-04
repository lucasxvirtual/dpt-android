package mapper

import com.drogariadopovo.domain.model.Prize
import response.PrizeResponse
import javax.inject.Inject

class PrizeMapper @Inject constructor() {

    fun map(response: List<PrizeResponse>) : List<Prize> {
        return response.map { map(it) }
    }

    fun map(response: PrizeResponse) : Prize {
        return Prize(
                typeName = response.typeName,
                level = response.level
        )
    }

}