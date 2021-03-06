/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.monitoring.spi.v3;

import com.google.api.MonitoredResource;
import com.google.api.gax.core.ConnectionSettings;
import com.google.api.gax.core.RetrySettings;
import com.google.api.gax.grpc.ApiCallSettings;
import com.google.api.gax.grpc.PageStreamingCallSettings;
import com.google.api.gax.grpc.PageStreamingDescriptor;
import com.google.api.gax.grpc.ServiceApiSettings;
import com.google.api.gax.grpc.SimpleCallSettings;
import com.google.auth.Credentials;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.monitoring.v3.CreateGroupRequest;
import com.google.monitoring.v3.DeleteGroupRequest;
import com.google.monitoring.v3.GetGroupRequest;
import com.google.monitoring.v3.Group;
import com.google.monitoring.v3.GroupServiceGrpc;
import com.google.monitoring.v3.ListGroupMembersRequest;
import com.google.monitoring.v3.ListGroupMembersResponse;
import com.google.monitoring.v3.ListGroupsRequest;
import com.google.monitoring.v3.ListGroupsResponse;
import com.google.monitoring.v3.UpdateGroupRequest;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.joda.time.Duration;

// AUTO-GENERATED DOCUMENTATION AND CLASS
/**
 * Settings class to configure an instance of {@link GroupServiceApi}.
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default service address (monitoring.googleapis.com) and default port (443) are used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 *
 * <p>The builder of this class is recursive, so contained classes are themselves builders. When
 * build() is called, the tree of builders is called to create the complete settings object. For
 * example, to set the total timeout of getGroup to 30 seconds:
 *
 * <pre>
 * <code>
 * GroupServiceSettings.Builder groupServiceSettingsBuilder =
 *     GroupServiceSettings.defaultBuilder();
 * groupServiceSettingsBuilder.getGroupSettings().getRetrySettingsBuilder()
 *     .setTotalTimeout(Duration.standardSeconds(30));
 * GroupServiceSettings groupServiceSettings = groupServiceSettingsBuilder.build();
 * </code>
 * </pre>
 */
@javax.annotation.Generated("by GAPIC")
public class GroupServiceSettings extends ServiceApiSettings {
  /** The default address of the service. */
  private static final String DEFAULT_SERVICE_ADDRESS = "monitoring.googleapis.com";

  /** The default port of the service. */
  private static final int DEFAULT_SERVICE_PORT = 443;

  /** The default scopes of the service. */
  private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES =
      ImmutableList.<String>builder().build();

  /** The default connection settings of the service. */
  public static final ConnectionSettings DEFAULT_CONNECTION_SETTINGS =
      ConnectionSettings.newBuilder()
          .setServiceAddress(DEFAULT_SERVICE_ADDRESS)
          .setPort(DEFAULT_SERVICE_PORT)
          .provideCredentialsWith(DEFAULT_SERVICE_SCOPES)
          .build();

  private final PageStreamingCallSettings<ListGroupsRequest, ListGroupsResponse, Group>
      listGroupsSettings;
  private final SimpleCallSettings<GetGroupRequest, Group> getGroupSettings;
  private final SimpleCallSettings<CreateGroupRequest, Group> createGroupSettings;
  private final SimpleCallSettings<UpdateGroupRequest, Group> updateGroupSettings;
  private final SimpleCallSettings<DeleteGroupRequest, Empty> deleteGroupSettings;
  private final PageStreamingCallSettings<
          ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>
      listGroupMembersSettings;

  /** Returns the object with the settings used for calls to listGroups. */
  public PageStreamingCallSettings<ListGroupsRequest, ListGroupsResponse, Group>
      listGroupsSettings() {
    return listGroupsSettings;
  }

  /** Returns the object with the settings used for calls to getGroup. */
  public SimpleCallSettings<GetGroupRequest, Group> getGroupSettings() {
    return getGroupSettings;
  }

  /** Returns the object with the settings used for calls to createGroup. */
  public SimpleCallSettings<CreateGroupRequest, Group> createGroupSettings() {
    return createGroupSettings;
  }

  /** Returns the object with the settings used for calls to updateGroup. */
  public SimpleCallSettings<UpdateGroupRequest, Group> updateGroupSettings() {
    return updateGroupSettings;
  }

  /** Returns the object with the settings used for calls to deleteGroup. */
  public SimpleCallSettings<DeleteGroupRequest, Empty> deleteGroupSettings() {
    return deleteGroupSettings;
  }

  /** Returns the object with the settings used for calls to listGroupMembers. */
  public PageStreamingCallSettings<
          ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>
      listGroupMembersSettings() {
    return listGroupMembersSettings;
  }

  /** Returns the default service address. */
  public static String getDefaultServiceAddress() {
    return DEFAULT_SERVICE_ADDRESS;
  }

  /** Returns the default service port. */
  public static int getDefaultServicePort() {
    return DEFAULT_SERVICE_PORT;
  }

  /** Returns the default service scopes. */
  public static ImmutableList<String> getDefaultServiceScopes() {
    return DEFAULT_SERVICE_SCOPES;
  }

  /** Returns a builder for this class with recommended defaults. */
  public static Builder defaultBuilder() {
    return Builder.createDefault();
  }

  /** Returns a new builder for this class. */
  public static Builder newBuilder() {
    return new Builder();
  }

  /** Returns a builder containing all the values of this settings class. */
  public Builder toBuilder() {
    return new Builder(this);
  }

  private GroupServiceSettings(Builder settingsBuilder) throws IOException {
    super(
        settingsBuilder.getChannelProvider(),
        settingsBuilder.getExecutorProvider(),
        settingsBuilder.getGeneratorName(),
        settingsBuilder.getGeneratorVersion(),
        settingsBuilder.getClientLibName(),
        settingsBuilder.getClientLibVersion());

    listGroupsSettings = settingsBuilder.listGroupsSettings().build();
    getGroupSettings = settingsBuilder.getGroupSettings().build();
    createGroupSettings = settingsBuilder.createGroupSettings().build();
    updateGroupSettings = settingsBuilder.updateGroupSettings().build();
    deleteGroupSettings = settingsBuilder.deleteGroupSettings().build();
    listGroupMembersSettings = settingsBuilder.listGroupMembersSettings().build();
  }

  private static final PageStreamingDescriptor<ListGroupsRequest, ListGroupsResponse, Group>
      LIST_GROUPS_PAGE_STR_DESC =
          new PageStreamingDescriptor<ListGroupsRequest, ListGroupsResponse, Group>() {
            @Override
            public Object emptyToken() {
              return "";
            }

            @Override
            public ListGroupsRequest injectToken(ListGroupsRequest payload, Object token) {
              return ListGroupsRequest.newBuilder(payload).setPageToken((String) token).build();
            }

            @Override
            public ListGroupsRequest injectPageSize(ListGroupsRequest payload, int pageSize) {
              return ListGroupsRequest.newBuilder(payload).setPageSize(pageSize).build();
            }

            @Override
            public Integer extractPageSize(ListGroupsRequest payload) {
              return payload.getPageSize();
            }

            @Override
            public Object extractNextToken(ListGroupsResponse payload) {
              return payload.getNextPageToken();
            }

            @Override
            public Iterable<Group> extractResources(ListGroupsResponse payload) {
              return payload.getGroupList();
            }
          };

  private static final PageStreamingDescriptor<
          ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>
      LIST_GROUP_MEMBERS_PAGE_STR_DESC =
          new PageStreamingDescriptor<
              ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>() {
            @Override
            public Object emptyToken() {
              return "";
            }

            @Override
            public ListGroupMembersRequest injectToken(
                ListGroupMembersRequest payload, Object token) {
              return ListGroupMembersRequest.newBuilder(payload)
                  .setPageToken((String) token)
                  .build();
            }

            @Override
            public ListGroupMembersRequest injectPageSize(
                ListGroupMembersRequest payload, int pageSize) {
              return ListGroupMembersRequest.newBuilder(payload).setPageSize(pageSize).build();
            }

            @Override
            public Integer extractPageSize(ListGroupMembersRequest payload) {
              return payload.getPageSize();
            }

            @Override
            public Object extractNextToken(ListGroupMembersResponse payload) {
              return payload.getNextPageToken();
            }

            @Override
            public Iterable<MonitoredResource> extractResources(ListGroupMembersResponse payload) {
              return payload.getMembersList();
            }
          };

  /** Builder for GroupServiceSettings. */
  public static class Builder extends ServiceApiSettings.Builder {
    private final ImmutableList<ApiCallSettings.Builder> methodSettingsBuilders;

    private final PageStreamingCallSettings.Builder<ListGroupsRequest, ListGroupsResponse, Group>
        listGroupsSettings;
    private final SimpleCallSettings.Builder<GetGroupRequest, Group> getGroupSettings;
    private final SimpleCallSettings.Builder<CreateGroupRequest, Group> createGroupSettings;
    private final SimpleCallSettings.Builder<UpdateGroupRequest, Group> updateGroupSettings;
    private final SimpleCallSettings.Builder<DeleteGroupRequest, Empty> deleteGroupSettings;
    private final PageStreamingCallSettings.Builder<
            ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>
        listGroupMembersSettings;

    private static final ImmutableMap<String, ImmutableSet<Status.Code>> RETRYABLE_CODE_DEFINITIONS;

    static {
      ImmutableMap.Builder<String, ImmutableSet<Status.Code>> definitions = ImmutableMap.builder();
      definitions.put(
          "idempotent",
          Sets.immutableEnumSet(
              Lists.<Status.Code>newArrayList(
                  Status.Code.DEADLINE_EXCEEDED, Status.Code.UNAVAILABLE)));
      definitions.put("non_idempotent", Sets.immutableEnumSet(Lists.<Status.Code>newArrayList()));
      RETRYABLE_CODE_DEFINITIONS = definitions.build();
    }

    private static final ImmutableMap<String, RetrySettings.Builder> RETRY_PARAM_DEFINITIONS;

    static {
      ImmutableMap.Builder<String, RetrySettings.Builder> definitions = ImmutableMap.builder();
      RetrySettings.Builder settingsBuilder = null;
      settingsBuilder =
          RetrySettings.newBuilder()
              .setInitialRetryDelay(Duration.millis(100L))
              .setRetryDelayMultiplier(1.3)
              .setMaxRetryDelay(Duration.millis(60000L))
              .setInitialRpcTimeout(Duration.millis(20000L))
              .setRpcTimeoutMultiplier(1.0)
              .setMaxRpcTimeout(Duration.millis(20000L))
              .setTotalTimeout(Duration.millis(600000L));
      definitions.put("default", settingsBuilder);
      RETRY_PARAM_DEFINITIONS = definitions.build();
    }

    private Builder() {
      super(DEFAULT_CONNECTION_SETTINGS);

      listGroupsSettings =
          PageStreamingCallSettings.newBuilder(
              GroupServiceGrpc.METHOD_LIST_GROUPS, LIST_GROUPS_PAGE_STR_DESC);

      getGroupSettings = SimpleCallSettings.newBuilder(GroupServiceGrpc.METHOD_GET_GROUP);

      createGroupSettings = SimpleCallSettings.newBuilder(GroupServiceGrpc.METHOD_CREATE_GROUP);

      updateGroupSettings = SimpleCallSettings.newBuilder(GroupServiceGrpc.METHOD_UPDATE_GROUP);

      deleteGroupSettings = SimpleCallSettings.newBuilder(GroupServiceGrpc.METHOD_DELETE_GROUP);

      listGroupMembersSettings =
          PageStreamingCallSettings.newBuilder(
              GroupServiceGrpc.METHOD_LIST_GROUP_MEMBERS, LIST_GROUP_MEMBERS_PAGE_STR_DESC);

      methodSettingsBuilders =
          ImmutableList.<ApiCallSettings.Builder>of(
              listGroupsSettings,
              getGroupSettings,
              createGroupSettings,
              updateGroupSettings,
              deleteGroupSettings,
              listGroupMembersSettings);
    }

    private static Builder createDefault() {
      Builder builder = new Builder();

      builder
          .listGroupsSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      builder
          .getGroupSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      builder
          .createGroupSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      builder
          .updateGroupSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      builder
          .deleteGroupSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      builder
          .listGroupMembersSettings()
          .setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))
          .setRetrySettingsBuilder(RETRY_PARAM_DEFINITIONS.get("default"));

      return builder;
    }

    private Builder(GroupServiceSettings settings) {
      super(settings);

      listGroupsSettings = settings.listGroupsSettings.toBuilder();
      getGroupSettings = settings.getGroupSettings.toBuilder();
      createGroupSettings = settings.createGroupSettings.toBuilder();
      updateGroupSettings = settings.updateGroupSettings.toBuilder();
      deleteGroupSettings = settings.deleteGroupSettings.toBuilder();
      listGroupMembersSettings = settings.listGroupMembersSettings.toBuilder();

      methodSettingsBuilders =
          ImmutableList.<ApiCallSettings.Builder>of(
              listGroupsSettings,
              getGroupSettings,
              createGroupSettings,
              updateGroupSettings,
              deleteGroupSettings,
              listGroupMembersSettings);
    }

    @Override
    protected ConnectionSettings getDefaultConnectionSettings() {
      return DEFAULT_CONNECTION_SETTINGS;
    }

    @Override
    public Builder provideExecutorWith(ScheduledExecutorService executor, boolean shouldAutoClose) {
      super.provideExecutorWith(executor, shouldAutoClose);
      return this;
    }

    @Override
    public Builder provideChannelWith(ManagedChannel channel, boolean shouldAutoClose) {
      super.provideChannelWith(channel, shouldAutoClose);
      return this;
    }

    @Override
    public Builder provideChannelWith(ConnectionSettings settings) {
      super.provideChannelWith(settings);
      return this;
    }

    @Override
    public Builder provideChannelWith(Credentials credentials) {
      super.provideChannelWith(credentials);
      return this;
    }

    @Override
    public Builder provideChannelWith(List<String> scopes) {
      super.provideChannelWith(scopes);
      return this;
    }

    @Override
    public Builder setGeneratorHeader(String name, String version) {
      super.setGeneratorHeader(name, version);
      return this;
    }

    @Override
    public Builder setClientLibHeader(String name, String version) {
      super.setClientLibHeader(name, version);
      return this;
    }

    /**
     * Applies the given settings to all of the API methods in this service. Only values that are
     * non-null will be applied, so this method is not capable of un-setting any values.
     */
    public Builder applyToAllApiMethods(ApiCallSettings.Builder apiCallSettings) throws Exception {
      super.applyToAllApiMethods(methodSettingsBuilders, apiCallSettings);
      return this;
    }

    /** Returns the builder for the settings used for calls to listGroups. */
    public PageStreamingCallSettings.Builder<ListGroupsRequest, ListGroupsResponse, Group>
        listGroupsSettings() {
      return listGroupsSettings;
    }

    /** Returns the builder for the settings used for calls to getGroup. */
    public SimpleCallSettings.Builder<GetGroupRequest, Group> getGroupSettings() {
      return getGroupSettings;
    }

    /** Returns the builder for the settings used for calls to createGroup. */
    public SimpleCallSettings.Builder<CreateGroupRequest, Group> createGroupSettings() {
      return createGroupSettings;
    }

    /** Returns the builder for the settings used for calls to updateGroup. */
    public SimpleCallSettings.Builder<UpdateGroupRequest, Group> updateGroupSettings() {
      return updateGroupSettings;
    }

    /** Returns the builder for the settings used for calls to deleteGroup. */
    public SimpleCallSettings.Builder<DeleteGroupRequest, Empty> deleteGroupSettings() {
      return deleteGroupSettings;
    }

    /** Returns the builder for the settings used for calls to listGroupMembers. */
    public PageStreamingCallSettings.Builder<
            ListGroupMembersRequest, ListGroupMembersResponse, MonitoredResource>
        listGroupMembersSettings() {
      return listGroupMembersSettings;
    }

    @Override
    public GroupServiceSettings build() throws IOException {
      return new GroupServiceSettings(this);
    }
  }
}
