package ru.bitrix24.api.deals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Deal {

    @SerializedName("ID")
    private String id;

    @SerializedName("TITLE")
    private String title;

    @SerializedName("TYPE_ID")
    private String typeId;

    @SerializedName("STAGE_ID")
    private String stageId;

    @SerializedName("PROBABILITY")
    private String probability;

    @SerializedName("CURRENCY_ID")
    private String currencyId;

    @SerializedName("OPPORTUNITY")
    private String opportunity;

    @SerializedName("IS_MANUAL_OPPORTUNITY")
    private String isManualOpportunity;

    @SerializedName("TAX_VALUE")
    private String taxValue;

    @SerializedName("LEAD_ID")
    private String leadId;

    @SerializedName("COMPANY_ID")
    private String companyId;

    @SerializedName("CONTACT_ID")
    private String contactId;

    @SerializedName("QUOTE_ID")
    private String quoteId;

    @SerializedName("BEGINDATE")
    private String beginDate;

    @SerializedName("CLOSEDATE")
    private String closeDate;

    @SerializedName("ASSIGNED_BY_ID")
    private String assignedById;

    @SerializedName("CREATED_BY_ID")
    private String createdById;

    @SerializedName("MODIFY_BY_ID")
    private String modifyById;

    @SerializedName("DATE_CREATE")
    private String dateCreate;

    @SerializedName("DATE_MODIFY")
    private String dateModify;

    @SerializedName("OPENED")
    private String opened;

    @SerializedName("CLOSED")
    private String closed;

    @SerializedName("COMMENTS")
    private String comments;

    @SerializedName("ADDITIONAL_INFO")
    private String additionalInfo;

    @SerializedName("LOCATION_ID")
    private String locationId;

    @SerializedName("CATEGORY_ID")
    private String categoryId;

    @SerializedName("STAGE_SEMANTIC_ID")
    private String stageSemanticId;

    @SerializedName("IS_NEW")
    private String isNew;

    @SerializedName("IS_RECURRING")
    private String isRecurring;

    @SerializedName("IS_RETURN_CUSTOMER")
    private String isReturnCustomer;

    @SerializedName("IS_REPEATED_APPROACH")
    private String isRepeatedApproach;

    @SerializedName("SOURCE_ID")
    private String sourceId;

    @SerializedName("SOURCE_DESCRIPTION")
    private String sourceDescription;

    @SerializedName("ORIGINATOR_ID")
    private String originatorId;

    @SerializedName("ORIGIN_ID")
    private String originId;

    @SerializedName("MOVED_BY_ID")
    private String movedById;

    @SerializedName("MOVED_TIME")
    private String movedTime;

    @SerializedName("LAST_ACTIVITY_TIME")
    private String lastActivityTime;

    @SerializedName("UTM_SOURCE")
    private String utmSource;

    @SerializedName("UTM_MEDIUM")
    private String utmMedium;

    @SerializedName("UTM_CAMPAIGN")
    private String utmCampaign;

    @SerializedName("UTM_CONTENT")
    private String utmContent;

    @SerializedName("UTM_TERM")
    private String utmTerm;

    @SerializedName("LAST_COMMUNICATION_TIME")
    private String lastCommunicationTime;

    @SerializedName("LAST_ACTIVITY_BY")
    private String lastActivityBy;
}
