DESCRIPTION = "Hello World"
SECTION = "libs"
LICENSE = "MIT"

inherit cmake

PV = "0"
PR = "r0"

SRC_URI = "\
file://hello.cpp \
file://CMakeLists.txt \
"
LIC_FILES_CHKSUM = "file://hello.cpp;md5=b00c3c6e99a10c3164ec4e83c738f515"
S = "${WORKDIR}"

do_compile() {
	cd ${S}
	cmake .
	make
}

do_install() {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/hello2 ${D}${bindir}/
}

FILE_${PN} = "${bindir}/hello2"
TARGET_CC_ARCH += "${LDFLAGS}"
