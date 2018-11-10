package com.sanjnan.rae.identityserver.couchbase;

import com.sanjnan.rae.identityserver.tokens.CouchbaseRefreshToken;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "couchbaseRefreshToken")
public interface CouchbaseRefreshTokenRepository extends CouchbasePagingAndSortingRepository<CouchbaseRefreshToken, String> {

  Optional<CouchbaseRefreshToken> findByTokenId(String tokenId);


}