#!/usr/bin/env bash

#######################################################
default_sbt_version=1.0.1
#######################################################


_bsd_="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

function quit_with { >&2 echo "ERROR: $@, quit"; exit 1; }

# Get sbt version from project, if defined
if [[ -f "${_bsd_}"/project/build.properties ]]; then
    _proj_sbt_ver=$(grep sbt.version "${_bsd_}"/project/build.properties)
    # Remove leading and trailing spaces
    _sbt_ver="$(echo ${_proj_sbt_ver#*=} || awk '{$1=$1};1')"
fi
sbt_ver="${_sbt_ver:-${default_sbt_version}}"
[[ -n "${sbt_ver}" ]] || quit_with "cannot find proper sbt version"

function fetch_sbt() {
    local tarball="sbt-${sbt_ver}.tgz"
    [[ -f "${tarball}" ]] || (
        local url="https://github.com/sbt/sbt/releases/download/v${sbt_ver}/${tarball}"
        wget "${url}" || curl -L "${url}"
    )
    mkdir -p "${sbt_ver}" && tar -zxvf "${tarball}" --strip-components=1 -C "$_"
}

[[ -d "${_bsd_}"/.sbt/latest ]] || (
    mkdir -p "${_bsd_}"/.sbt && cd $_
    [[ -d "${sbt_ver}" ]] || fetch_sbt
    ln -nfs "${sbt_ver}" latest
)

exec ${_bsd_}/.sbt/latest/bin/sbt $@
