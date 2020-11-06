package com.drogariadopovo.domain.repository

import com.drogariadopovo.domain.model.Voucher
import io.reactivex.Single

interface VoucherRepository {

    fun getVouchers() : Single<List<Voucher>>
}