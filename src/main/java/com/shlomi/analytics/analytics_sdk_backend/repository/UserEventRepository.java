package com.shlomi.analytics.analytics_sdk_backend.repository;


import com.shlomi.analytics.analytics_sdk_backend.model.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends MongoRepository<UserEvent, String> {

}