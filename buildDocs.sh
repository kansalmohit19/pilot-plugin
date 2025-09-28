#!/bin/bash

set -e

if [[ "$OSTYPE" == "darwin"* ]]; then
  SEDOPTION="-i ''"   # macOS
else
  SEDOPTION="-i"      # Linux
fi

DOCS_ROOT=docs
DOCS_ROOT_GIT_VERSION=$DOCS_ROOT/git-version

if [ -d "$DOCS_ROOT" ]; then
  rm -r "$DOCS_ROOT"
fi

mkdir -p "$DOCS_ROOT"
mkdir -p "$DOCS_ROOT_GIT_VERSION"

#building docs for pilotplugin
cp README.md $DOCS_ROOT/index.md
cp LICENSE.md $DOCS_ROOT/license.md

#building docs for git-version
cp git-version/README.md $DOCS_ROOT_GIT_VERSION/index.md
cp LICENSE.md $DOCS_ROOT_GIT_VERSION/license.md
cp git-version/PUBLISHING.md $DOCS_ROOT_GIT_VERSION/publishing.md
cp git-version/USAGE.md $DOCS_ROOT_GIT_VERSION/usage.md


eval sed $SEDOPTION -e 's/LICENSE.md/license/g' "$DOCS_ROOT_GIT_VERSION/index.md"
eval sed $SEDOPTION -e 's/LICENSE.md/license/g' "$DOCS_ROOT/index.md"

