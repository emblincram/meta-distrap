# SPDX-License-Identifier: MIT
# SPDX-Author: Roman Koch <koch.romam@gmail.com>
# SPDX-Copyright: 2024 Roman Koch <koch.romam@gmail.com>

SUMMARY = "Simple Distributet Application Example"
DESCRIPTION = "First test with embedded applications on multiple modules"

LICENSE = "MIT"
# bitbake -e | grep ^COMMON_LICENSE_DIR
# md5sum LICENSE
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*   Simple Distributed Application Example    *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

# Hier wird das externe Application-Repository eingebunden

SRC_URI = "git://github.com/emblincram/distrap.git;branch=main;protocol=ssh"
#SRC_URI = "git:///mnt/ssd/work/distrap;branch=main;protocol=file"

SRCREV = "2de2fccbfd89eb7c4509033171d287585fa01e29"
## scheint buggy zu sein, später: "SRCREV = ${AUTOREV}" oder "SRCREV = "HEAD""

S = "${WORKDIR}/git"

DEPENDS = "cmake"

inherit cmake

#do_install() {
#    install -d ${D}${bindir}
#    install -m 0755 ${B}/test ${D}${bindir}/test
#    install -m 0755 ${B}/core ${D}${bindir}/core
#    install -m 0755 ${B}/shell ${D}${bindir}/shell
#}

do_install() {
    #oe_runmake install DESTDIR=${D}
    cmake --install ${B} --prefix=${D}/usr
}
