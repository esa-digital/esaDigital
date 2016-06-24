 </main>

    <footer class="group js-footer" id="footer" role="contentinfo">
        <div class="footer-wrapper">
            <div class="footer-meta">
                <div class="footer-meta-inner">
                    <div class="open-government-licence">
                        <p class="logo"><a href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/" rel="license">Open Government Licence</a></p>
                        <p>All content is available under the <a href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/" rel="license">Open Government Licence v3.0</a>, except where otherwise stated</p>
                    </div>
                </div>
                <div class="copyright">
                    <a href="http://www.nationalarchives.gov.uk/information-management/re-using-public-sector-information/copyright-and-re-use/crown-copyright/">&copy; Crown copyright</a>
                </div>
            </div>
        </div>
    </footer>
    <div id="global-app-error" class="app-error hidden"></div>
    
    <#if pageJs != "">
      <script src="assets/javascripts/${pageJs}.js"></script>
    </#if>
    <script src="assets/javascripts/govuk-template.js"></script>
</body>
</html>