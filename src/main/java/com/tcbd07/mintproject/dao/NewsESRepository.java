package com.tcbd07.mintproject.dao;

import com.tcbd07.mintproject.entity.Nd_News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description
 * @Author 赵泽明
 * @Version 1.0
 */

public interface NewsESRepository extends ElasticsearchRepository<Nd_News,String> {
}
