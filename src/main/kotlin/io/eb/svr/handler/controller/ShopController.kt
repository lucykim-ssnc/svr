package io.eb.svr.handler.controller

import io.eb.svr.common.config.ApiConfig.API_VERSION
import io.eb.svr.common.config.ApiConfig.CONFIRM_PATH
import io.eb.svr.common.config.ApiConfig.EMPLOYEES_PATH
import io.eb.svr.common.config.ApiConfig.IMAGE_PATH
import io.eb.svr.common.config.ApiConfig.SETTING_PATH
import io.eb.svr.common.config.ApiConfig.SHOP_PATH
import io.eb.svr.common.config.ApiConfig.TIME_PATH
import io.eb.svr.common.config.ApiConfig.UPLOAD_PATH
import io.eb.svr.handler.entity.request.EmployeeRequest
import io.eb.svr.handler.entity.request.ShopOperationTimeRequest
import io.eb.svr.handler.service.ShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

/**
 * Create by lucy on 2019-06-10
 **/
@RestController
@RequestMapping(
	path = ["/$API_VERSION/$SHOP_PATH"]
)
class ShopController {
	@Autowired
	private lateinit var shopService: ShopService

	@PostMapping(
		path = ["/$UPLOAD_PATH/$IMAGE_PATH"],
		consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE),
		produces = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
	fun fileUpload(
		@RequestParam("files") file: MultipartFile
	) = ResponseEntity.status(OK).body(shopService.fileUpload(file))

	@GetMapping(
		path = ["/$SETTING_PATH/{shopId}"],
		produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getShopSetting(
		servlet: HttpServletRequest,
		@PathVariable("shopId") shopId: Long
	) = ResponseEntity.status(OK).body(shopService.getShopSetting(servlet, shopId))

	@PutMapping(
		path = ["/$SETTING_PATH"],
		consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE)
//		,produces = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
	)
	fun putShop(
		servlet: HttpServletRequest,
		@RequestParam("files") files: List<MultipartFile>,
		@RequestParam("shop") request: String
	) = ResponseEntity.status(OK).body(shopService.putShop(request, files))

	@PostMapping(
		path = ["/$SETTING_PATH/$TIME_PATH"],
		consumes = [MediaType.APPLICATION_JSON_VALUE],
		produces = [MediaType.APPLICATION_JSON_VALUE])
	fun shopOperationTimeSetting(
		servlet: HttpServletRequest,
		@RequestBody request: ShopOperationTimeRequest
	) = ResponseEntity.status(CREATED).body(shopService.shopOperationTimeSetting(request))

	@GetMapping(
		path = ["/$EMPLOYEES_PATH/{shopId}"],
		produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getShopEmployees(
		servlet: HttpServletRequest,
		@PathVariable("shopId") shopId: Long
	) = ResponseEntity.status(OK).body(shopService.getShopEmployees(servlet, shopId))

	@PutMapping(
		path = ["/$EMPLOYEES_PATH/$CONFIRM_PATH"],
		consumes = [MediaType.APPLICATION_JSON_VALUE])
	fun putEmployee(
		servlet: HttpServletRequest,
		@RequestBody request: EmployeeRequest
	) = ResponseEntity.status(OK).body(shopService.putEmployee(servlet, request))

}