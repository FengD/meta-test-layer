DESCRIPTION = "Hello World"

SECTION = "libs"

LICENSE = "MIT"

PV = "3"

PR = "r0"

SRC_URI = "\
file://hello.c \
file://Makefile \
"
LIC_FILES_CHKSUM = "file://hello.c;md5=d41d8cd98f00b204e9800998ecf8427e"
S = "${WORKDIR}"

do_compile() {
	make
}

do_install() {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/hello ${D}${bindir}/
}

FILE_${PN} = "${bindir}/hello"
TARGET_CC_ARCH += "${LDFLAGS}"
