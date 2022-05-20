package com.example.websiteblog.utils.RoleConstant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class RoleConstant {
    @Getter
    @AllArgsConstructor
    public enum RoleUser {
        ROLE_ADMIN(1),
        ROLE_USER(2),
        OTHER(0);
        private Integer value;

        private static final Map<Integer, RoleUser> mappingValue = new HashMap<>();

        static {
            for (RoleUser roleUser : RoleUser.values()) {
                mappingValue.put(roleUser.getValue(), roleUser);
            }
        }
        @JsonCreator
        public static RoleUser fromValue(Integer value) {
            return mappingValue.getOrDefault(value, OTHER);
        }
    }


}
