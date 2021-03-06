/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.resourcemanager;

import com.google.api.services.cloudresourcemanager.model.Binding;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.Policy.Marshaller;
import com.google.cloud.Role;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class PolicyMarshaller
    extends Marshaller<com.google.api.services.cloudresourcemanager.model.Policy> {

  static final PolicyMarshaller INSTANCE = new PolicyMarshaller();

  private PolicyMarshaller() {}

  private static class Builder extends Policy.Builder {

    private Builder(Map<Role, Set<Identity>> bindings, String etag, Integer version) {
      bindings(bindings);
      etag(etag);
      if (version != null) {
        version(version);
      }
    }
  }

  @Override
  protected Policy fromPb(com.google.api.services.cloudresourcemanager.model.Policy policyPb) {
    Map<Role, Set<Identity>> bindings = new HashMap<>();
    if (policyPb.getBindings() != null) {
      for (Binding bindingPb : policyPb.getBindings()) {
        bindings.put(Role.of(bindingPb.getRole()), ImmutableSet.copyOf(
            Lists.transform(bindingPb.getMembers(), IDENTITY_VALUE_OF_FUNCTION)));
      }
    }
    return new Builder(bindings, policyPb.getEtag(), policyPb.getVersion()).build();
  }

  @Override
  protected com.google.api.services.cloudresourcemanager.model.Policy toPb(Policy policy) {
    com.google.api.services.cloudresourcemanager.model.Policy policyPb =
        new com.google.api.services.cloudresourcemanager.model.Policy();
    List<Binding> bindingPbList =
        new LinkedList<>();
    for (Map.Entry<Role, Set<Identity>> binding : policy.bindings().entrySet()) {
      Binding bindingPb = new Binding();
      bindingPb.setRole(binding.getKey().value());
      bindingPb.setMembers(
          Lists.transform(new ArrayList<>(binding.getValue()), IDENTITY_STR_VALUE_FUNCTION));
      bindingPbList.add(bindingPb);
    }
    policyPb.setBindings(bindingPbList);
    policyPb.setEtag(policy.etag());
    policyPb.setVersion(policy.version());
    return policyPb;
  }
}
