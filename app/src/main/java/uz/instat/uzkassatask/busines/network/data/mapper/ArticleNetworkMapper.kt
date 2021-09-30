package uz.instat.fitneskittest.busines.network.data.mapper

import uz.instat.fitneskittest.busines.network.NetworkMapper
import uz.instat.fitneskittest.busines.network.data.ArticleDomain
import uz.instat.uzkassatask.busines.local.ArticleLocal
import uz.instat.uzkassatask.busines.network.data.mapper.SourceNetworkMapper
import javax.inject.Inject

class ArticleNetworkMapper @Inject constructor(
    private val sourceNetworkMapper: SourceNetworkMapper
) :
    NetworkMapper<ArticleDomain, ArticleLocal>() {
    override fun mapFromDomain(domain: ArticleDomain): ArticleLocal {
        return ArticleLocal(
            source = sourceNetworkMapper.mapFromDomain(domain.source),
            author = domain.author,
            title = domain.title,
            description = domain.description,
            url = domain.url,
            urlToImage = domain.urlToImage,
            publishedAt = domain.publishedAt,
            content = domain.content
        )
    }

    override fun mapFromLocal(local: ArticleLocal): ArticleDomain {
        return ArticleDomain(
            source = sourceNetworkMapper.mapFromLocal(local.source),
            author = local.author,
            title = local.title,
            description = local.description,
            url = local.url,
            urlToImage = local.urlToImage,
            publishedAt = local.publishedAt,
            content = local.content
        )
    }
}