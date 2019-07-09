package com.meeting.bean;

import java.util.ArrayList;
import java.util.List;

public class MeetingSigninExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MeetingSigninExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIsNull() {
            addCriterion("meeting_id is null");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIsNotNull() {
            addCriterion("meeting_id is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingIdEqualTo(Integer value) {
            addCriterion("meeting_id =", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotEqualTo(Integer value) {
            addCriterion("meeting_id <>", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdGreaterThan(Integer value) {
            addCriterion("meeting_id >", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("meeting_id >=", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdLessThan(Integer value) {
            addCriterion("meeting_id <", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdLessThanOrEqualTo(Integer value) {
            addCriterion("meeting_id <=", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIn(List<Integer> values) {
            addCriterion("meeting_id in", values, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotIn(List<Integer> values) {
            addCriterion("meeting_id not in", values, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdBetween(Integer value1, Integer value2) {
            addCriterion("meeting_id between", value1, value2, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("meeting_id not between", value1, value2, "meetingId");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNull() {
            addCriterion("signin_time is null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNotNull() {
            addCriterion("signin_time is not null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeEqualTo(Long value) {
            addCriterion("signin_time =", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotEqualTo(Long value) {
            addCriterion("signin_time <>", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThan(Long value) {
            addCriterion("signin_time >", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("signin_time >=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThan(Long value) {
            addCriterion("signin_time <", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThanOrEqualTo(Long value) {
            addCriterion("signin_time <=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIn(List<Long> values) {
            addCriterion("signin_time in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotIn(List<Long> values) {
            addCriterion("signin_time not in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeBetween(Long value1, Long value2) {
            addCriterion("signin_time between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotBetween(Long value1, Long value2) {
            addCriterion("signin_time not between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninFlagIsNull() {
            addCriterion("signin_flag is null");
            return (Criteria) this;
        }

        public Criteria andSigninFlagIsNotNull() {
            addCriterion("signin_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSigninFlagEqualTo(Boolean value) {
            addCriterion("signin_flag =", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagNotEqualTo(Boolean value) {
            addCriterion("signin_flag <>", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagGreaterThan(Boolean value) {
            addCriterion("signin_flag >", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("signin_flag >=", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagLessThan(Boolean value) {
            addCriterion("signin_flag <", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("signin_flag <=", value, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagIn(List<Boolean> values) {
            addCriterion("signin_flag in", values, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagNotIn(List<Boolean> values) {
            addCriterion("signin_flag not in", values, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("signin_flag between", value1, value2, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andSigninFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("signin_flag not between", value1, value2, "signinFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagIsNull() {
            addCriterion("leave_flag is null");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagIsNotNull() {
            addCriterion("leave_flag is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagEqualTo(Boolean value) {
            addCriterion("leave_flag =", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagNotEqualTo(Boolean value) {
            addCriterion("leave_flag <>", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagGreaterThan(Boolean value) {
            addCriterion("leave_flag >", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("leave_flag >=", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagLessThan(Boolean value) {
            addCriterion("leave_flag <", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("leave_flag <=", value, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagIn(List<Boolean> values) {
            addCriterion("leave_flag in", values, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagNotIn(List<Boolean> values) {
            addCriterion("leave_flag not in", values, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("leave_flag between", value1, value2, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaveFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("leave_flag not between", value1, value2, "leaveFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagIsNull() {
            addCriterion("late_flag is null");
            return (Criteria) this;
        }

        public Criteria andLateFlagIsNotNull() {
            addCriterion("late_flag is not null");
            return (Criteria) this;
        }

        public Criteria andLateFlagEqualTo(Boolean value) {
            addCriterion("late_flag =", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagNotEqualTo(Boolean value) {
            addCriterion("late_flag <>", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagGreaterThan(Boolean value) {
            addCriterion("late_flag >", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("late_flag >=", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagLessThan(Boolean value) {
            addCriterion("late_flag <", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("late_flag <=", value, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagIn(List<Boolean> values) {
            addCriterion("late_flag in", values, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagNotIn(List<Boolean> values) {
            addCriterion("late_flag not in", values, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("late_flag between", value1, value2, "lateFlag");
            return (Criteria) this;
        }

        public Criteria andLateFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("late_flag not between", value1, value2, "lateFlag");
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