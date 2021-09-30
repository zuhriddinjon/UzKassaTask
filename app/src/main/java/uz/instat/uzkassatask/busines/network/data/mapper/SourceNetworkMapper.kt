package uz.instat.uzkassatask.busines.network.data.mapper

import uz.instat.fitneskittest.busines.network.NetworkMapper
import uz.instat.fitneskittest.busines.network.data.SourceDomain
import uz.instat.fitneskittest.busines.network.data.SourceLocal
import javax.inject.Inject

class SourceNetworkMapper @Inject constructor() : NetworkMapper<SourceDomain, SourceLocal>() {
    override fun mapFromDomain(domain: SourceDomain): SourceLocal {
        return SourceLocal(
            id = domain.id,
            name = domain.name
        )
    }

    override fun mapFromLocal(local: SourceLocal): SourceDomain {
        return SourceDomain(
            id = local.id,
            name = local.name
        )
    }
}