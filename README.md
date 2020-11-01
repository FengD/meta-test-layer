# (Yocto) How to create your own meta-layer?

## 1. DESCRIPTION
In this project, I will show you how to create your own meta-layer for by yocto, bitbake command.
And this project is the result by follow the course below. Let's start.
This project is for the developer who has basic knowledge of yocto.

## 2. Steps

### 2.1. Enviroment
* Use the command `source <path_to_oe-init-build-env>`, which will give you the environment needed.


### 2.2. Creation
* Use `bitbake-layers create-layer <path_you_want_to_put_your_layer>` to create the layer.
* Then you will find the folder as below with the `tree` command. It means that your creation is successful.

```sh
├── conf
│   └── layer.conf
├── COPYING.MIT
├── README
└── recipes-example
    └── example
        └── example_0.1.bb
```

### 2.3. Addition
* Use the command `bitbake-layers add-layer <path_of_your_layer>`
* And then you will fould in the `conf/bblayers.conf`, your layer was added into the list. If you build the image now, it will be built successfully with an empty layer.
```sh
BBLAYERS ?= " \
  /yocto/poky/meta \
  /yocto/poky/meta-poky \
  /yocto/poky/meta-yocto-bsp \
  /yocto/poky/meta-raspberrypi \
  /yocto/poky/meta-test-layer \
  "
```

### 2.4. Create an App

* 2.4.1. You could add your own recipe in the `recipes-example` or create a new recipe with the same structure of `recipes-example`
```sh
cd recipes-example
mkdir test_hello
cd test_hello
mkdir hello
touch hello.bb
mkdir hello2
touch hello2.bb
cd hello
touch hello.c
touch Makefile
```

```sh
.
├── conf
│   └── layer.conf
├── COPYING.MIT
├── README
└── recipes-example
    ├── example
    │   └── example_0.1.bb
    └── test_hello
        ├── hello
        │   ├── hello.c
        │   └── Makefile
        ├── hello2
        │   ├── hello.c
        │   └── Makefile
        ├── hello2.bb
        └── hello.bb
ps: the folder name and the name of the .bb file should be the same.
```

* 2.4.2. Put the following content in the `hello.bb` file.

``` sh
DESCRIPTION = "Hello World"
SECTION = "libs"
LICENSE = "MIT"
PV = "1"
PR = "r0"

SRC_URI = "\
file://hello.c \
file://Makefile \
"
LIC_FILES_CHKSUM = "file://hello.c;md5=7b9ca335e1a89cba4c18bab96dcc6d8c"

do_compile() {
	make
}

do_install() {
	install -d ${D}${bindir}/
	install -m 0755 ${WORKDIR}/hello ${D}${bindir}/
}

FILE_${PN} = "${bindir}/hello"
TARGET_CC_ARCH += "${LDFLAGS}"
```

* 2.4.3. Fill the code and makefile to the .c and Makefile

``` c
// C++

#include "stdio.h"
#include "stdlib.h"
int main() {
	printf("hello world!!\n");
	return 0;
}
```

``` makefile
#Makefile

hello: hello.o
	$(CC) -o hello hello.o


hello.o: hello.c
	$(CC) -c hello.c

.PHONY: clean
clean:
	-rm -rf *.o
```

* 2.4.4. Check if the application was added successfully.
``` sh
bitbake -s |grep hello
```

* 2.4.5. Build
``` sh
bitbake hello
```

* 2.4.6. Add the application in the image. Find your <image.bb> file and add the app at the end of the `IMAGE_INSTALL` list.
``` sh
IMAGE_INSTALL += " \
kernel-modules \
hello \
"
```

* 2.4.7. Build your image and you will find your app in the `/usr/bin/`.

## 3. All the content below is generated automatically.
```
This README file contains information on the contents of the meta-ding-test layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: <first dependency>
  branch: <branch name>

  URI: <second dependency>
  branch: <branch name>

  .
  .
  .

Patches
=======

Please submit any patches against the meta-test-layer layer to the xxxx mailing list (xxxx@zzzz.org)
and cc: the maintainer:

Maintainer: XXX YYYYYY <xxx.yyyyyy@zzzzz.com>

Table of Contents
=================

  I. Adding the meta-test-layer layer to your build
 II. Misc


I. Adding the meta-test-layer layer to your build
=================================================

Run 'bitbake-layers add-layer meta-test-layer'

II. Misc
========

--- replace with specific information about the meta-ding-test layer ---
```
