LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-subdir-java-files)

LOCAL_PACKAGE_NAME := PPCServer
LOCAL_CERTIFICATE := platform
LOCAL_OVERRIDES_PACKAGES := dongle_launcher Aml2DLauncher_mbx

include $(BUILD_PACKAGE)
#include $(call all-makefiles-under,$(LOCAL_PATH))

