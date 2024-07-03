import globals from "globals";
import pluginJs from "@eslint/js";
import eslintConfigPrettier from "eslint-config-prettier";
import babelParser from "@babel/eslint-parser";

export default [
  {
    ignores: ["**/dist"],
  },
  {
    languageOptions: {
      globals: {
        ...globals.browser,
        ...globals.es2021,
        ...globals.node,
      },
      parser: babelParser,
      parserOptions: {
        requireConfigFile: false,
      },
    },

  },
  pluginJs.configs.recommended,
  eslintConfigPrettier,
];
