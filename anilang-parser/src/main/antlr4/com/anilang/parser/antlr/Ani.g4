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
    |   '\n'+
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
    |   '\n'
    ;

scriptLine
    :   variableDeclarator
    |   statement
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
    |   'return' expression?
    |   'break'
    |   'continue'
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


Identifier
    :   [a-zA-Z_]+
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
