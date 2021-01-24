package com.ai.st.microservice.notifier.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.st.microservice.notifier.business.NotificationBusiness;
import com.ai.st.microservice.notifier.dto.BasicResponseDto;
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

@Api(value = "Manage Users-Roles", description = "Manage Users-Roles", tags = { "Administration" })
@RestController
@RequestMapping("api/notifier/v1/")
public class NotificationController {

	private final Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Value("${st.site.url}")
	public String siteURL;

	@Value("${st.site.email}")
	public String siteEmail;

	@Autowired
	private NotificationBusiness notificationBusiness;

	@RequestMapping(value = "/list/{userCode}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get list notifications")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Notifications List", response = List.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> notificationsList(@PathVariable(name = "userCode", required = true) Long userCode,
			@PathVariable(name = "status", required = true) int status) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {
			responseDto = notificationBusiness.getNotifications(userCode, status);
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			log.error("Error WorkspaceV1Controller@createWorkspace#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new BasicResponseDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

	@RequestMapping(value = "/notify/change_status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get list notifications")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Notifications List", response = NotificationChangeStatusDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> notificationsChangeStatus(@RequestBody NotificationChangeStatusDto req) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {
			responseDto = notificationBusiness.changeNotificationStatus(req.getNotificationId(), req.getStatus());
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			log.error("Error WorkspaceV1Controller@createWorkspace#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new BasicResponseDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

	/** Nuevo Usuario */
	@RequestMapping(value = "/notify/new_user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationNewUser(
			@RequestBody NotificationNewUserDto requestCreateNotification) {

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

	/** Asignación Gestión Municipio */
	@RequestMapping(value = "/notify/municipality_management_assignment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationMunicipalityManagementAssignment(
			@RequestBody NotificationMunicipalityManagementDto requestCreateNotification) {

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

	/** Asignación Operación Municipio */
	@RequestMapping(value = "/notify/assignment_operation_municipality", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationAssignmentOperationMunicipality(
			@RequestBody NotificationAssignmentOperationMunicipalityDto requestCreateNotification) {

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

	/** Solicitud de Insumos */
	@RequestMapping(value = "/notify/input_request", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationInputRequest(
			@RequestBody NotificationInputRequestDto requestCreateNotification) {

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

	/** Cargue de Insumo XTF */
	@RequestMapping(value = "/notify/load_of_inputs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationLoadOfInputs(
			@RequestBody NotificationLoadOfInputsDto requestCreateNotification) {

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

	/** Integración de Insumos XTF */
	@RequestMapping(value = "/notify/input_integrations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationInputIntegrations(
			@RequestBody NotificationInputIntegrationsDto requestCreateNotification) {

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

	/** Asignación de Tarea */
	@RequestMapping(value = "/notify/task_assignment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationTaskAssignment(
			@RequestBody NotificationTaskAssignmentDto requestCreateNotification) {

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

	/** Generación de archivo de integración XTF */
	@RequestMapping(value = "/notify/integration_file_generation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationIntegrationFileGeneration(
			@RequestBody NotificationIntegrationFileGenerationDto requestCreateNotification) {

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

	/** Entrega de Insumos */
	@RequestMapping(value = "/notify/delivery_of_inputs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> sendNotificationDeliveryOfInputs(
			@RequestBody NotificationDeliveryOfInputsDto requestCreateNotification) {

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

	/** Recuperar cuenta */
	@RequestMapping(value = "/notify/recover-account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create notification")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Email sent", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> recoverAccount(@RequestBody NotificationRecoverAccountDto requestRecoverAccount) {

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

	public ResponseEntity<Object> newNotification(NotificationDto notification) {
		HttpStatus httpStatus = null;
		Object responseDto = null;
		try {
			responseDto = notificationBusiness.createNotification(notification.getUserCode(), notification.getEmail(),
					notification.getSubject(), notification.getMessage(), notification.getType());
			httpStatus = HttpStatus.CREATED;
		} catch (NotificationException e) {
			log.error("Error NotificationController@createNotification#Business ---> " + e.getMessage());
			httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			responseDto = new BasicResponseDto(e.getMessage(), 2);
		} catch (Exception e) {
			log.error("Error WorkspaceV1Controller@createWorkspace#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new BasicResponseDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

}
