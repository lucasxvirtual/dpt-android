package mapper

import com.drogariadopovo.domain.model.Voucher
import response.VoucherResponse
import javax.inject.Inject

class VoucherMapper @Inject constructor(){
    fun map(response : List<VoucherResponse>) : List<Voucher> {
        return response.map { map(it)!! }
    }

    fun map(response : VoucherResponse?) : Voucher? {
        if(response == null)
            return null
        return Voucher(
                id = response.id,
                title = response.title,
                image = response.image,
                expirationDate = response.expirationDate,
                used = response.used,
                user = response.user
        )
    }
}