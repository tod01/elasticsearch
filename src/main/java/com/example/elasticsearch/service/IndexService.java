package com.example.elasticsearch.service;

import com.example.elasticsearch.helper.Indices;
import com.example.elasticsearch.helper.Util;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class IndexService {
    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;
    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices() {
        recreateIndices(false);
    }

    public void recreateIndices(final boolean deleteExisting) {

        final String settings = Util.loadAsString("static/es-settings.json");

        if(settings == null) {
            LOG.error("Failed to load index settings !");
            return;
        }

        for(final String indexName: INDICES_TO_CREATE) {
            try {
                boolean indexExists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }

                    client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
                }

                final String mappings = loadMappings(indexName);

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

            }catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private String loadMappings(String indexName) {

        final String mappings = Util.loadAsString("static/mappings/" + indexName + ".json");

        if (mappings == null) {
            LOG.error("Failed to create index with name '{}' ", indexName);
            return null;
        }

        return mappings;
    }
}
