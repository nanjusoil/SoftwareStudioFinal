# [Coding Styles](https://google.github.io/styleguide/javaguide.html)
+ �ӵۤ@�w��Coding Style���ӷ|�ݰ_�Ӥ���n�@�I

# [ControlP5 -- a proccesing library](http://www.sojamo.de/libraries/controlP5/)
+ �зӥ[�Jcore.jar���Ҧ��[�JControlP5.jar
+ �o��jar���ܦh���ت���k�i�H�ΡA�ڥثe�O�ΨӰ����s(core.jar�S�����s)

# �sApplet
+ �{�b���y�{�O Main(�إ�JFrame)->�إ�MainApplet ���k�� ->�إ�LoginApplet(����Applet���ഫ)
+ �H�����ӷ|�O Main -> LoginApplet ->�C�ӳ����@��Applet

# �p��إ߷s��Applet
+ 					loginapplet.init();  //�@�w�n��l��
+					loginapplet.start();
+					loginapplet.setFocusable(true);
+                   jframe.setContentPane(loginapplet); //��Applet�[��JFrame

#Item����
+ ���|��Type: CONTROL, TOOL, MESSAGE, FURNITURE
+ CONTROL:����C�����i��ex:�����ж������k��,��l(�]���i�H�����I��)
+ TOOL:�b�ж��̾ߨ�A�i�H�b���U�Ӫ��k��L�{�����W�γ�ex:key
+ MESSAGE:ex:�ж��̾ߨ쪺�ȹΡA�u�}��|������
+ FURNITURE: ex: �O�I�c,�ϵe�A����Q����

#�C���y�{
+ TOOL
+ �ߨ�Item(�D��)/call ItemBox��putinItem()
+ (���쥿�T������)
+ �b�D�����I�@�U�D��(�N���_�D��)/call ItemBox��checkItem()
+ �b���T����m�I�@�U�A�D��N�|�o���@�Ψî���/call ItemBox��useItem()
+ solControlP5�N�O���a�b���_�D�����I����m

+ MESSAGE
+ �ߨ�ȱ�/call ItemBox��putinItem()
+ �b�D�����I�@�U�D��i�H�d�ݥ������e/call ItemBox��checkItem()
+ ��F��S�w����ɡA�ȱ��N�|����(ex:�O�I�c���}��A�g���O�I�c�K�X���ȱ��N�|����)/call ItemBox��useItem()
