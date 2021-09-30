package uz.instat.fitneskittest.busines.network

import android.util.Log

abstract class NetworkMapper<Domain, Local> {
    abstract fun mapFromDomain(domain: Domain): Local
    abstract fun mapFromLocal(local: Local): Domain
    open fun mapFromDomainList(list: List<Domain>): List<Local> {
        return ArrayList<Local>().apply {
            list.forEach {
                add(mapFromDomain(it))
            }
        }
    }

    open fun mapFromLocalList(list: List<Local>): List<Domain> {
        return ArrayList<Domain>().apply {
            list.forEach {
                add(mapFromLocal(it))
            }
        }
    }
}