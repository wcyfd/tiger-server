
echo off
rem ==============�����ǿ��Ըĵı���=================
rem Ŀ������
set TARGET=as
rem Դ������λ��
set code_target_dir=..\src\com\randioo\configClass
rem ѹ�������λ��
set assets_zip_target_dir=..\src\assets
rem =================================================

set ZIP_SOURCE_DIR=.\out
set ZIP_OUT_DIR=.\out
set ZIP_FILE_PREFIX=tbl

set source_dir=.\out

rem ���ɴ��������
..\..\..\jre7\bin\java -Dfile.encoding=utf-8 -jar .\randioo-excel-0.0.1-SNAPSHOT.jar %TARGET%

if not exist out\*.tbl (
	echo no files product.
	) else (

rem ѹ��tbl�ļ�
7za a -tzip %ZIP_OUT_DIR%/config.cfg %ZIP_SOURCE_DIR%/*.%ZIP_FILE_PREFIX%

rem �ƶ�Դ���ļ������ñ����ݰ���ָ���ļ���
mkdir %code_target_dir%
mkdir %assets_zip_target_dir%

copy /y %source_dir%\*.%TARGET% %code_target_dir%
copy /y %source_dir%\*.cfg %assets_zip_target_dir%
)
pause