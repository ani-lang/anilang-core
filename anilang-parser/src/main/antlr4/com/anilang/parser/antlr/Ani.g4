grammar Ani;

file
    :   body EOF
    ;

body
    :   bodyMember*
    ;

bodyMember
    :   scriptLine
    |   funcDeclaration
    |   classDeclaration
    |   structDeclaration
    |   '\n'+
    ;

structDeclaration
    :   'struct' Identifier structBody
    ;

structBody
    :   ':' '\n' structBodyMember* '\n' 'end'
    ;

type
    :   Identifier ('[' ']')*
    |   primitiveType ('[' ']')*
    ;

structBodyMember
    :   type Identifier (IntegerLiteral (',' IntegerLiteral)*)?
    |   '\n'
    ;

classDeclaration
    :   'class' Identifier classBody
    ;

classBody
    :   ':' '\n' classBodyMember* '\n' 'end'
    ;

classBodyMember
    :   variableDeclarator
    |   funcDeclaration
    |   structDeclaration
    |   '\n'
    ;

scriptLine
    :   variableDeclarator
    |   statement
    |   expression
    |   expression '(' expressionList? ')'
    |   '\n'+
    ;

funcDeclaration
    :   'def' Identifier formalParameters methodDeclarationRest
    ;

variableDeclarator
    :   variableDeclaratorId '=' variableInitializer
    ;

formalParameters
    :   '(' formalParameterDecls? ')'
    ;

formalParameterDecls
    :   formalParameterDeclsRest
    ;

formalParameterDeclsRest
    :   variableDeclaratorId (',' formalParameterDecls)?
    ;

methodDeclarationRest
    :   methodBody
    ;

methodBody
    :   scriptBlock
    ;

scriptBlock
    :   ':' '\n' scriptLine* '\n' 'end'
    ;

statement
    :   'if' expression elseScriptBlock
    |   'while' expression scriptBlock
    |   'for' forControl scriptBlock
    |   'match' expression matchScriptBlock
    |   'return' expression?
    |   'break'
    |   'continue'
    ;

matchScriptBlock
    :   ':' '\n' matchDeclaration* '\n' 'end'
    ;

matchDeclaration
    :   matchLabel
    |   '\n'
    ;

matchLabel
    :   'case' expression scriptBlock
    |   'case' Identifier scriptBlock
    |   'default' scriptBlock
    ;

elseScriptBlock
    :   ':' '\n' scriptLine* ('else' '\n' scriptLine*)* '\n' 'end'
    ;

forControl
    :   itemForControl
    ;

itemForControl
    :   Identifier 'in' expression
    ;

variableDeclaratorId
    :   Identifier
    ;

variableInitializer
    :   expression
    |   arrayInitializer
    ;

arrayInitializer
    :   '[' (variableInitializer (',' variableInitializer)* (',')? )? ']'
    ;

expression
    :   primary
    |   expression '.' Identifier
    |   expression ('+'|'-') expression
    |   expression ('*'|'/') expression
    |   expression ('=' | '!=') expression
    |   expression ('<' '=' | '>' '=' | '<' | '>') expression
    |   expression 'and' expression
    |   expression 'or' expression
    |   expression '(' expressionList? ')'
    ;

primary
    :   literal
    |   Identifier
    |   '(' expression ')'
    ;

literal
    :   IntegerLiteral
    |   DecimalLiteral
    |   booleanLiteral
    |   StringLiteral
    ;

booleanLiteral
    :   'true'
    |   'false'
    ;

expressionList
    :   expression (',' expression)*
    ;

primitiveType
    :   'boolean'
    |   'int'
    |   'decimal'
    |   'string'
    ;

Identifier
    :   [a-zA-Z_]+[a-zA-Z_0-9]*
    ;

IntegerLiteral
    :   ('0' | '1'..'9' '0'..'9'*)
    ;

DecimalLiteral
    :   ('0'..'9')+ '.' ('0'..'9')*
    ;

StringLiteral
    :   '\'' .*? '\''
    ;

// toss out whitespace
WS  :   [ \t]+ -> skip ;

COMMENT
    :   '/*' .*? '*/'    -> skip
    ;

LINE_COMMENT
    :   '#' ~[\r\n]* -> skip
    ;
