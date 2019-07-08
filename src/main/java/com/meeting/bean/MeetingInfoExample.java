package com.meeting.bean;

import java.util.ArrayList;
import java.util.List;

public class MeetingInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MeetingInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDepartIdsIsNull() {
            addCriterion("depart_ids is null");
            return (Criteria) this;
        }

        public Criteria andDepartIdsIsNotNull() {
            addCriterion("depart_ids is not null");
            return (Criteria) this;
        }

        public Criteria andDepartIdsEqualTo(String value) {
            addCriterion("depart_ids =", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsNotEqualTo(String value) {
            addCriterion("depart_ids <>", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsGreaterThan(String value) {
            addCriterion("depart_ids >", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsGreaterThanOrEqualTo(String value) {
            addCriterion("depart_ids >=", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsLessThan(String value) {
            addCriterion("depart_ids <", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsLessThanOrEqualTo(String value) {
            addCriterion("depart_ids <=", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsLike(String value) {
            addCriterion("depart_ids like", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsNotLike(String value) {
            addCriterion("depart_ids not like", value, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsIn(List<String> values) {
            addCriterion("depart_ids in", values, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsNotIn(List<String> values) {
            addCriterion("depart_ids not in", values, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsBetween(String value1, String value2) {
            addCriterion("depart_ids between", value1, value2, "departIds");
            return (Criteria) this;
        }

        public Criteria andDepartIdsNotBetween(String value1, String value2) {
            addCriterion("depart_ids not between", value1, value2, "departIds");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameIsNull() {
            addCriterion("insert_username is null");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameIsNotNull() {
            addCriterion("insert_username is not null");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameEqualTo(String value) {
            addCriterion("insert_username =", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameNotEqualTo(String value) {
            addCriterion("insert_username <>", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameGreaterThan(String value) {
            addCriterion("insert_username >", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("insert_username >=", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameLessThan(String value) {
            addCriterion("insert_username <", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameLessThanOrEqualTo(String value) {
            addCriterion("insert_username <=", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameLike(String value) {
            addCriterion("insert_username like", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameNotLike(String value) {
            addCriterion("insert_username not like", value, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameIn(List<String> values) {
            addCriterion("insert_username in", values, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameNotIn(List<String> values) {
            addCriterion("insert_username not in", values, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameBetween(String value1, String value2) {
            addCriterion("insert_username between", value1, value2, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andInsertUsernameNotBetween(String value1, String value2) {
            addCriterion("insert_username not between", value1, value2, "insertUsername");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Integer value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Integer value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Integer value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Integer value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Integer value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Integer> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Integer> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Integer value1, Integer value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andLateTimeIsNull() {
            addCriterion("late_time is null");
            return (Criteria) this;
        }

        public Criteria andLateTimeIsNotNull() {
            addCriterion("late_time is not null");
            return (Criteria) this;
        }

        public Criteria andLateTimeEqualTo(Integer value) {
            addCriterion("late_time =", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeNotEqualTo(Integer value) {
            addCriterion("late_time <>", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeGreaterThan(Integer value) {
            addCriterion("late_time >", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("late_time >=", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeLessThan(Integer value) {
            addCriterion("late_time <", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("late_time <=", value, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeIn(List<Integer> values) {
            addCriterion("late_time in", values, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeNotIn(List<Integer> values) {
            addCriterion("late_time not in", values, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeBetween(Integer value1, Integer value2) {
            addCriterion("late_time between", value1, value2, "lateTime");
            return (Criteria) this;
        }

        public Criteria andLateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("late_time not between", value1, value2, "lateTime");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeIsNull() {
            addCriterion("refresh_qcode is null");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeIsNotNull() {
            addCriterion("refresh_qcode is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeEqualTo(Boolean value) {
            addCriterion("refresh_qcode =", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeNotEqualTo(Boolean value) {
            addCriterion("refresh_qcode <>", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeGreaterThan(Boolean value) {
            addCriterion("refresh_qcode >", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("refresh_qcode >=", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeLessThan(Boolean value) {
            addCriterion("refresh_qcode <", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeLessThanOrEqualTo(Boolean value) {
            addCriterion("refresh_qcode <=", value, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeIn(List<Boolean> values) {
            addCriterion("refresh_qcode in", values, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeNotIn(List<Boolean> values) {
            addCriterion("refresh_qcode not in", values, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeBetween(Boolean value1, Boolean value2) {
            addCriterion("refresh_qcode between", value1, value2, "refreshQcode");
            return (Criteria) this;
        }

        public Criteria andRefreshQcodeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("refresh_qcode not between", value1, value2, "refreshQcode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}