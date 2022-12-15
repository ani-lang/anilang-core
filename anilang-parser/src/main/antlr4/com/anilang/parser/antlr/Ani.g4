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
    |   NEWLINE+
    ;

structDeclaration
    :   'struct' Identifier structBody
    ;

structBody
    :   ':' NEWLINE structBodyMember* NEWLINE 'end'
    ;

type
    :   Identifier ('[' ']')*
    |   primitiveType ('[' ']')*
    ;

structBodyMember
    :   type Identifier (IntegerLiteral (',' IntegerLiteral)*)?
    |   NEWLINE
    ;

classDeclaration
    :   'class' Identifier classBody
    ;

classBody
    :   ':' NEWLINE classBodyMember* NEWLINE 'end'
    ;

classBodyMember
    :   variableDeclarator
    |   funcDeclaration
    |   structDeclaration
    |   sqlDeclarator
    |   NEWLINE
    ;

scriptLine
    :   variableDeclarator
    |   statement
    |   expression
    |   expression '(' expressionList? ')'
    |   sqlDeclarator
    |   NEWLINE+
    ;

sqlDeclarator
    :   inlineSqlClause expression sqlScriptBlock?
    ;

inlineSqlClause
    :   'select>'
    |   'update>'
    |   'delete>'
    |   'insert>'
    ;

sqlScriptBlock
    :   ':' NEWLINE sqlScriptBody NEWLINE 'end'
    ;

sqlScriptBody
    :   sqlSelectStatement
    ;

sqlSelectStatement
    :   sqlExpressionList NEWLINE
        'from' Identifier (',' Identifier)* NEWLINE
        sqlWhereClause
    ;

sqlWhereClause
    :   'where' sqlExpression
    ;

sqlExpression
    :   primary
    |   '*'
    |   sqlExpression '.' (Identifier | '*')
    |   sqlExpression ('+'|'-') sqlExpression
    |   sqlExpression ('*'|'/') sqlExpression
    |   sqlExpression ('=' | '!=') sqlExpression
    |   sqlExpression ('<' '=' | '>' '=' | '<' | '>') sqlExpression
    |   sqlExpression 'and' sqlExpression
    |   sqlExpression 'or' sqlExpression
    |   sqlExpression '(' sqlExpressionList? ')'
    ;

sqlExpressionList
    :   sqlExpression (',' sqlExpression)*
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
    :   ':' NEWLINE scriptLine* NEWLINE 'end'
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
    :   ':' NEWLINE matchDeclaration* NEWLINE 'end'
    ;

matchDeclaration
    :   matchLabel
    |   NEWLINE
    ;

matchLabel
    :   'case' expression scriptBlock
    |   'case' Identifier scriptBlock
    |   'default' scriptBlock
    ;

elseScriptBlock
    :   ':' NEWLINE scriptLine* ('else' NEWLINE scriptLine*)* NEWLINE 'end'
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
    |   expression ('==' | '!=') expression
    |   expression ('<' '=' | '>' '=' | '<' | '>') expression
    |   expression 'and' expression
    |   expression 'or' expression
    |   expression '(' expressionList? ')'
    |   expression '=' expression
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

NEWLINE
    : '\r'? '\n'
    ;
