package com.hun2.server.service.exceptoin

abstract class ResourceNotFoundException(descriptionOfResource: String):
    BaseException(descriptionOfResource, ErrorCode.RESOURCE_NOT_FOUND)

class UserNotFoundException(descriptionOfResource: String = "고객정보"): ResourceNotFoundException(descriptionOfResource)
class VacationNotFoundException(descriptionOfResource: String = "방학정보"): ResourceNotFoundException(descriptionOfResource)
