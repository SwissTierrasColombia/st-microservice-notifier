package com.ai.st.microservice.notifier.controllers;

import java.util.List;

import com.ai.st.microservice.common.dto.general.BasicResponseDto;
import com.ai.st.microservice.notifier.services.tracing.SCMTracing;
import com.ai.st.microservice.notifier.services.tracing.TracingKeyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ai.st.microservice.notifier.business.NotificationBusiness;
import com.ai.st.microservice.notifier.dto.NotificationAssignmentOperationMunicipalityDto;
import com.ai.st.microservice.notifier.dto.NotificationChangeStatusDto;
import com.ai.st.microservice.notifier.dto.NotificationDeliveryOfInputsDto;
import com.ai.st.microservice.notifier.dto.NotificationDto;
import com.ai.st.microservice.notifier.dto.NotificationInputIntegrationsDto;
import com.ai.st.microservice.notifier.dto.NotificationInputRequestDto;
import com.ai.st.microservice.notifier.dto.NotificationIntegrationFileGenerationDto;
import com.ai.st.microservice.notifier.dto.NotificationLoadOfInputsDto;
import com.ai.st.microservice.notifier.dto.NotificationMunicipalityManagementDto;
import com.ai.st.microservice.notifier.dto.NotificationNewUserDto;
import com.ai.st.microservice.notifier.dto.NotificationRecoverAccountDto;
import com.ai.st.microservice.notifier.dto.NotificationTaskAssignmentDto;
import com.ai.st.microservice.notifier.exceptions.NotificationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Manage Users-Roles", tags = { "Administration" })
@RestController
@RequestMapping("api/notifier/v1/")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Value("${st.site.url}")
    public String siteURL;

    @Value("${st.site.email}")
    public String siteEmail;

    private final NotificationBusiness notificationBusiness;

    public NotificationController(NotificationBusiness notificationBusiness) {
        this.notificationBusiness = notificationBusiness;
    }

    @GetMapping(value = "/list/{userCode}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get list notifications")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notifications List", response = List.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> getNotificationsByUser(@PathVariable(name = "userCode") Long userCode,
            @PathVariable(name = "status") int status) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("getNotificationsByUser");

            responseDto = notificationBusiness.getNotifications(userCode, status);
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            log.error("Error WorkspaceV1Controller@getNotificationsByUser#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping(value = "/notify/change_status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get list notifications")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Notifications List", response = NotificationChangeStatusDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> changeNotificationStatus(@RequestBody NotificationChangeStatusDto req) {

        HttpStatus httpStatus;
        Object responseDto;

        try {

            SCMTracing.setTransactionName("changeNotificationStatus");
            SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, req.toString());

            responseDto = notificationBusiness.changeNotificationStatus(req.getNotificationId(), req.getStatus());
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            log.error("Error WorkspaceV1Controller@changeNotificationStatus#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping(value = "/notify/new_user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationNewUser(@RequestBody NotificationNewUserDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationNewUser");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/municipality_management_assignment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationMunicipalityManagementAssignment(
            @RequestBody NotificationMunicipalityManagementDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationMunicipalityManagementAssignment");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/assignment_operation_municipality", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationAssignmentOperationMunicipality(
            @RequestBody NotificationAssignmentOperationMunicipalityDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationAssignmentOperationMunicipality");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/input_request", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationInputRequest(
            @RequestBody NotificationInputRequestDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationInputRequest");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/load_of_inputs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationLoadOfInputs(
            @RequestBody NotificationLoadOfInputsDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationLoadOfInputs");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/input_integrations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationInputIntegrations(
            @RequestBody NotificationInputIntegrationsDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationInputIntegrations");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/task_assignment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationTaskAssignment(
            @RequestBody NotificationTaskAssignmentDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationTaskAssignment");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/integration_file_generation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationIntegrationFileGeneration(
            @RequestBody NotificationIntegrationFileGenerationDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationIntegrationFileGeneration");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/delivery_of_inputs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notification sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationDeliveryOfInputs(
            @RequestBody NotificationDeliveryOfInputsDto requestCreateNotification) {

        SCMTracing.setTransactionName("sendNotificationDeliveryOfInputs");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestCreateNotification.toString());

        requestCreateNotification.setSiteEmail(siteEmail);
        requestCreateNotification.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestCreateNotification.getUserCode());
        notification.setEmail(requestCreateNotification.getEmail());
        notification.setSubject(requestCreateNotification.getSubject());
        notification.setMessage(requestCreateNotification.getBody());
        notification.setType(requestCreateNotification.getType());
        return this.newNotification(notification);
    }

    @PostMapping(value = "/notify/recover-account", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create notification")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Email sent", response = NotificationDto.class),
            @ApiResponse(code = 500, message = "Error Server", response = String.class) })
    @ResponseBody
    public ResponseEntity<?> sendNotificationRecoverAccount(
            @RequestBody NotificationRecoverAccountDto requestRecoverAccount) {

        SCMTracing.setTransactionName("sendNotificationRecoverAccount");
        SCMTracing.addCustomParameter(TracingKeyword.BODY_REQUEST, requestRecoverAccount.toString());

        requestRecoverAccount.setSiteEmail(siteEmail);
        requestRecoverAccount.setSiteURL(siteURL);

        NotificationDto notification = new NotificationDto();
        notification.setUserCode(requestRecoverAccount.getUserCode());
        notification.setEmail(requestRecoverAccount.getEmail());
        notification.setSubject(requestRecoverAccount.getSubject());
        notification.setMessage(requestRecoverAccount.getBody());
        notification.setType(requestRecoverAccount.getType());
        return this.newNotification(notification);
    }

    public ResponseEntity<?> newNotification(NotificationDto notification) {
        HttpStatus httpStatus;
        Object responseDto;
        try {
            responseDto = notificationBusiness.createNotification(notification.getUserCode(), notification.getEmail(),
                    notification.getSubject(), notification.getMessage(), notification.getType());
            httpStatus = HttpStatus.CREATED;
        } catch (NotificationException e) {
            log.error("Error NotificationController@newNotification#Business ---> " + e.getMessage());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        } catch (Exception e) {
            log.error("Error NotificationController@newNotification#General ---> " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDto = new BasicResponseDto(e.getMessage());
            SCMTracing.sendError(e.getMessage());
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

}
