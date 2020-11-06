package repository

import api.ConstantsApi
import com.drogariadopovo.domain.model.Constants
import com.drogariadopovo.domain.repository.ConstantsRepository
import io.reactivex.Single
import mapper.ConstantsMapper
import storage.Singleton
import javax.inject.Inject

class ConstantsRepositoryImpl @Inject constructor(
        private val constantsApi: ConstantsApi,
        private val constantsMapper: ConstantsMapper
) : ConstantsRepository {

    override fun getConstants(): Single<Constants> {
        return constantsApi.getConstants().map { constantsMapper.map(it) }
    }

    override fun saveConstants(constants: Constants) {
        Singleton.instance.setConstants(constants)
    }
}