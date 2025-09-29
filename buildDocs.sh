#!/bin/bash

set -e

if [[ "$OSTYPE" == "darwin"* ]]; then
  SEDOPTION="-i ''"   # macOS
else
  SEDOPTION="-i"      # Linux
fi

DOCS_ROOT=docs

if [ -d "$DOCS_ROOT" ]; then
  rm -r "$DOCS_ROOT"
fi

mkdir -p "$DOCS_ROOT"
mkdir -p "$DOCS_ROOT/git-version"
mkdir -p "$DOCS_ROOT/ktext"

#building docs for pilotplugin
cp README.md $DOCS_ROOT/index.md
cp LICENSE.md $DOCS_ROOT/license.md

#building docs for git-version
cp git-version/README.md $DOCS_ROOT/git-version/index.md
cp LICENSE.md $DOCS_ROOT/git-version/license.md
cp git-version/PUBLISHING.md $DOCS_ROOT/git-version/publishing.md
cp git-version/USAGE.md $DOCS_ROOT/git-version/usage.md

#building docs for ktext
cp ktext/README.md $DOCS_ROOT/ktext/index.md
cp LICENSE.md $DOCS_ROOT/ktext/license.md
cp ktext/PUBLISHING.md $DOCS_ROOT/ktext/publishing.md
cp ktext/USAGE.md $DOCS_ROOT/ktext/usage.md

eval sed $SEDOPTION -e 's/LICENSE.md/license/g' "$DOCS_ROOT/index.md"
eval sed $SEDOPTION -e 's/LICENSE.md/license/g' "$DOCS_ROOT/git-version/index.md"
eval sed $SEDOPTION -e 's/LICENSE.md/license/g' "$DOCS_ROOT/ktext/index.md"

